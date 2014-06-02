(defproject cljx-sample "0.1.0-SNAPSHOT"
  :description "Sample program of client and server side validation using cljx"
  :url "https://github.com/snufkon/cljx-sample"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/clj" "target/gen-src/clj"]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [ring/ring-core "1.3.0-RC1"]
                 [hiccup "1.0.5"]
                 [com.novemberain/validateur "2.1.0"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [domina "1.0.3-SNAPSHOT"]
                 [crate "0.2.5"]]
  :hooks [cljx.hooks]
  :plugins [[lein-ring "0.8.2"]
            [lein-cljsbuild "1.0.3"]
            [com.keminglabs/cljx "0.3.2"]]
  :ring {:handler cljx-sample.core/app}
  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/gen-src/clj"
                   :rules :clj}
                  {:source-paths ["src/cljx"]
                   :output-path "target/gen-src/cljs"
                   :rules :cljs}]}
  :cljsbuild {:builds
              [{:source-paths ["src/cljs" "target/gen-src/cljs"]
                :compiler {:output-to "resources/public/js/main.js"
                           :optimizations :whitespace
                           :pretty-print true}}]})
