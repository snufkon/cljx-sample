(ns cljx-sample.core
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.form :refer [label text-field submit-button form-to]]
            [hiccup.page :refer [html5 include-css include-js]]
            [cljx-sample.views.common :refer [error-message]]
            [cljx-sample.validation.core :refer [wrap-validation on-error]]
            [cljx-sample.validation.user :refer [valid-user?]]))

(defn- frame
  [contents]
  (html5
   [:head
    (include-css "/css/normalize.css")
    (include-css "/css/main.css")
    [:title "cljx sample"]
    [:meta {:charset "utf-8"}]]
   [:body
    contents
    (include-js "/js/main.js")]))

(defn- index-page
  [params]
  (frame
   (form-to [:post "/"]
            (label "name" "名前")
            (text-field "name" (:name params))
            (on-error :name error-message)

            (label "email" "メールアドレス")
            (text-field "email" (:email params))
            (on-error :email error-message)

            (submit-button {:id "create"} "作成"))))

(defn- create-user
  [params]
  (str "名前: " (:name params) "<br>"
       "メールアドレス: " (:email params)))

(defroutes app-routes
  (GET "/" [& params] (index-page params))
  (POST "/" [& params] (if (valid-user? params)
                         (create-user params)
                         (index-page params)))

  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (-> app-routes
      handler/site
      wrap-validation))
