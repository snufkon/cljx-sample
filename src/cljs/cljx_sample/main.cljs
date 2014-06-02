(ns cljx-sample.main
  (:require [domina :refer [by-id by-class value destroy! insert-after!]]
            [domina.css :refer [sel]]
            [domina.events :refer [listen! prevent-default]]
            [cljx-sample.views.common :refer [error-message]]
            [cljx-sample.validation.core :refer [get-errors on-error]]
            [cljx-sample.validation.user :refer [valid-user?]]))

(enable-console-print!)

(defn- current-params
  []
  {:name (-> "input[name='name']" sel value)
   :email (-> "input[name='email']" sel value)})

(defn- disp-errors
  []
  (destroy! (by-class "error"))
  (doseq [[key errs] (get-errors)]
    (insert-after! (by-id (name key))
                   (on-error key error-message))))

(defn- init
  []
  (listen! (by-id "create")
           :click (fn [event]
                    (when-not (valid-user? (current-params))
                      (prevent-default event)
                      (disp-errors)))))

(set! (.-onload js/window) init)
