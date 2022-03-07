(ns backend.server.routes)

(def routes
  ["/hello-world"
   {:get {:summary    "Hello World!"
          :responses  {200 {:body {:hello string?}}}
          :handler    (fn [_]
                        {:status 200
                         :body   {:hello "world"}})}}])
