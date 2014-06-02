(ns cljx-sample.validation.user
  (:require [validateur.validation :refer [validation-set presence-of format-of invalid?]]
            [cljx-sample.validation.core :refer [clear-errors! set-errors! no-errors?]]))

(def email-pattern #"^[a-zA-Z0-9.!#$&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");p

(defn valid-user?
  [params]
  (let [vs (validation-set
            (presence-of :name)
            (format-of :email
                       :format email-pattern
                       :message "has incorrect mail format"))]
    (clear-errors!)
    (when (invalid? vs params)
      (set-errors! (vs params)))
    (no-errors?)))
