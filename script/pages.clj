(ns pages
  (:require
   [clojure.edn :as edn]
   [babashka.tasks :refer [shell]]
   [babashka.fs :as fs]
   [babashka.curl :as curl]))

(def creds 
   (-> (slurp "creds.edn")
       (edn/read-string)))


(def host "http://localhost:8080")
(def prefix "/api/pages")

(defn users []
  (-> (curl/get (str host prefix "/users")
            ; {:headers {"Content-Type" "application/text"}}
                )
      :body
      edn/read-string
      ))

(defn user-pages [user]
  (-> (curl/get (str host prefix "/user-pages?user=" user)
            ; {:headers {"Content-Type" "application/text"}}
                )
      :body
      edn/read-string))

(defn user-page [user page]
  (-> (curl/get (str host prefix "/page?user=" user "&page=" page)
              {:headers {"Accept" "application/edn"}})
      :body
      (edn/read-string)
      ))

(defn publish [user password page hiccup]
  (-> (curl/post (str host prefix "/page?user=" user "&page=" page "&password=" password)
             {:headers {"Content-Type" "application/text"
                         "Accept" "application/edn"}
              :body (pr-str hiccup)})
      :body
      ;type
      edn/read-string
      ;(get "body")
      ))


(defn snippet-list []
  (map str (fs/glob "snippets" "**{.edn,.clj,cljc}")))

(defn random-snippet []
  (let [snippets (into [] (snippet-list))
        index (rand-int (count snippets))]
    (get snippets index)))

