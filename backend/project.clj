(defproject backend "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[metosin/reitit "0.5.16"]
                 [org.clojure/clojure "1.10.1"]
                 [ring/ring-jetty-adapter "1.9.5"]]
  :repl-options {:init-ns backend.server.server}
  :main ^:skip-aot backend.server.server)
