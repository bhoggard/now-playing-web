(ns now-playing-web.prod
  (:require [now-playing-web.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
