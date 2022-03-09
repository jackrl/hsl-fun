(defproject backend "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[cheshire "5.10.2" :exclusions [com.fasterxml.jackson.core/jackson-core]]
                 [clj-http "3.12.3"]
                 [metosin/reitit "0.5.16"]
                 [org.clojure/clojure "1.10.3"]
                 [ring/ring-jetty-adapter "1.9.5"]
                 [ring/ring-mock "0.4.0"]
                 [tortue/spy "2.9.0"]]
  :repl-options {:init-ns backend.server.server}
  :main ^:skip-aot backend.server.server)
