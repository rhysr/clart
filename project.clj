(defproject cart "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :ring {:handler cart.web/app
         :init cart.web/init
         :destroy cart.web/destroy}
  :plugins [[lein-ring "0.8.12"]]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.2.0"]
                 [ring-server "0.3.1"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
             :production {:ring {:open-browser? false, :stacktraces? false, :auto-reload? false}}
             :dev {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.3.1"]]
             :ring {:open-browser? false}}
  )
