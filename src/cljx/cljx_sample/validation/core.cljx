(ns cljx-sample.validation.core)

#+cljs (enable-console-print!)

(def ^:dynamic *errors* (atom {}))

(defn clear-errors!
  []
  (reset! *errors* {}))

(defn get-errors
  [& [field]]
  (if field
    (get @*errors* field)
    @*errors*))

(defn set-errors!
  [errs]
  (reset! *errors* errs))

(defn on-error
  [field func]
  (if-let [errs (get-errors field)]
    (func errs)))

(defn errors?
  [& [field]]
  (not (empty? (get-errors field))))

(def no-errors? (complement errors?))

#+clj
(defn wrap-validation
  [handler]
  (fn [request]
    (binding [*errors* (atom {})]
      (handler request))))
