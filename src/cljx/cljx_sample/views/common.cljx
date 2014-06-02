(ns cljx-sample.views.common
  #+cljs  (:use-macros [crate.def-macros :only [defhtml]])
  (:require #+clj [hiccup.def :refer [defhtml]]
            #+cljs [crate.core :refer [crate]]))

(defhtml error-message
  [errors]
  [:span.error (first errors)])
