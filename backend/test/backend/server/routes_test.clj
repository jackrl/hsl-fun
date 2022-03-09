(ns backend.server.routes-test
  (:require [clojure.test :refer :all]
            [backend.server.routes :as r]
            [muuntaja.core :as m]
            [reitit.coercion.spec]
            [reitit.dev.pretty :as pretty]
            [reitit.ring :as ring]
            [reitit.ring.coercion :as coercion]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.exception :as exception]
            [reitit.ring.middleware.multipart :as multipart]
            [reitit.ring.middleware.parameters :as parameters]
            [ring.mock.request :as mock]
            [spy.core :as spy]))

(def test-app
  (ring/ring-handler
    (ring/router
      [r/routes]

      {
       :exception pretty/exception
       :data      {:coercion   reitit.coercion.spec/coercion
                   :muuntaja   m/instance
                   :middleware [;; query-params & form-params
                                parameters/parameters-middleware
                                ;; content-negotiation
                                muuntaja/format-negotiate-middleware
                                ;; exception handling
                                (exception/create-exception-middleware
                                  {::exception/default (partial exception/wrap-log-to-console exception/default-handler)})
                                ;; decoding request body
                                muuntaja/format-request-middleware
                                ;; coercing response bodys
                                coercion/coerce-response-middleware
                                ;; coercing request parameters
                                coercion/coerce-request-middleware]}})))

(deftest get-stops-by-radius
  (testing "POST /api/stops"
    (testing "Responds correctly with status 200 when 3 stops are found"
      (with-redefs [backend.api.hsl/get-hsl-nodes-in-radius (spy/spy (constantly [{:distance 225, :stop {:name "Pasilan asema", :lat 60.1981, :lon 24.93365, :wheelchairBoarding "POSSIBLE"}}
                                                                                  {:distance 261, :stop {:name "Pasilan asema", :lat 60.198075, :lon 24.933694, :wheelchairBoarding "NO_INFORMATION"}}
                                                                                  {:distance 294, :stop {:name "Pasilan asema", :lat 60.198002, :lon 24.933908, :wheelchairBoarding "NO_INFORMATION"}}]))]
        (let [lat    60.19803
              lon    24.93129
              radius 230
              response (test-app (-> (mock/request :post "/api/stops")
                                     (mock/json-body {:lat    lat
                                                      :lon    lon
                                                      :radius radius})))]
          (is (spy/called-with? backend.api.hsl/get-hsl-nodes-in-radius lat lon radius))
          (is (= (:status response) 200))
          (is (= (:body response) {:stops
                                   [{:name       "Pasilan asema",
                                     :lat        60.1981,
                                     :lon        24.93365,
                                     :distance   225,
                                     :accessible true}
                                    {:name       "Pasilan asema",
                                     :lat        60.198075,
                                     :lon        24.933694,
                                     :distance   261,
                                     :accessible false}
                                    {:name       "Pasilan asema",
                                     :lat        60.198002,
                                     :lon        24.933908,
                                     :distance   294,
                                     :accessible false}]})))))

    (testing "Responds correctly with status 200 when 1 stop is found"
      (with-redefs [backend.api.hsl/get-hsl-nodes-in-radius (spy/spy (constantly [{:distance 225, :stop {:name "Pasilan asema", :lat 60.1981, :lon 24.93365, :wheelchairBoarding "POSSIBLE"}}]))]
        (let [lat    60.19803
              lon    24.93129
              radius 230
              response (test-app (-> (mock/request :post "/api/stops")
                                     (mock/json-body {:lat    lat
                                                      :lon    lon
                                                      :radius radius})))]
          (is (spy/called-with? backend.api.hsl/get-hsl-nodes-in-radius lat lon radius))
          (is (= (:status response) 200))
          (is (= (:body response) {:stops
                                   [{:name       "Pasilan asema",
                                     :lat        60.1981,
                                     :lon        24.93365,
                                     :distance   225,
                                     :accessible true}]})))))

    (testing "Responds correctly with status 200 when no stops are found"
      (with-redefs [backend.api.hsl/get-hsl-nodes-in-radius (spy/spy (constantly []))]
        (let [lat    60.19803
              lon    24.93129
              radius 230
              response (test-app (-> (mock/request :post "/api/stops")
                                     (mock/json-body {:lat    lat
                                                      :lon    lon
                                                      :radius radius})))]
          (is (spy/called-with? backend.api.hsl/get-hsl-nodes-in-radius lat lon radius))
          (is (= (:status response) 200))
          (is (= (:body response) {:stops []})))))))
