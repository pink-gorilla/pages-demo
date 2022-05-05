(ns scratchpad
  (:require
   [clojure.edn :as edn]
   [babashka.tasks :refer [shell]]
   [babashka.fs :as fs]
   [babashka.curl :as curl]))

(defn show [hiccup]
  (curl/post "http://localhost:8080/api/scratchpad" 
             {:headers {"Content-Type" "application/text"} 
              :body (pr-str hiccup)})) 
  

(defn snippet-list []
  (map str (fs/glob "snippets" "**{.edn,.clj,cljc}")))

(defn random-snippet []
  (let [snippets (into [] (snippet-list))
        index (rand-int (count snippets))]
    (get snippets index)))

(defn show-random []
  (let [name (random-snippet)]
    (println "showing: " name)
    (-> name slurp edn/read-string show)
    )
  )
