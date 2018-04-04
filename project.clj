(defproject datascript-schema "0.1.0"
  :description "Datomic-like schema"
  :url "http://github.com/fmnasution/datascript-schema"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/"]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.8.0"]
                                  [datascript "0.16.4"]
                                  [org.clojure/tools.nrepl "0.2.13"]
                                  [org.clojure/tools.namespace "0.3.0-alpha4"]]
                   :plugins [[refactor-nrepl "2.4.0-SNAPSHOT"]
                             [cider/cider-nrepl "0.17.0-SNAPSHOT"]]}}
  :aliases {"dev" ["with-profile"
                   "+dev"
                   "do" "clean," "repl" ":headless"]})
