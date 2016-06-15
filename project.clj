(defproject gr "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-time  "0.12.0"]
                 [compojure  "1.5.0"]
                 [ring  "1.5.0"]
                 [ring/ring-defaults  "0.2.1"]
                 [org.clojure/data.json  "0.2.6"]
                 [prismatic/schema  "1.1.2"]]
  :main ^:skip-aot gr.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
