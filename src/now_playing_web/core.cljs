(ns now-playing-web.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

;; -------------------------
;; Utility functions

(def api-url "http://now-playing-api.herokuapp.com")

(defn update-piece [data source]
  (go (let [response (<! (http/get (str api-url source) {:with-credentials? false}))]
    response)))

;; -------------------------
;; Views

(defn piece [name url source]
  (let [component-state (reagent/atom {:title "" :composer ""})]
    (fn []  ; render function is from here down
      ; (js/setTimeout #(swap! component-state update-piece source) 5 * 1000)
      (swap! component-state update-piece source)
      [:div
      [:h2 [:a {:href url :target "_blank"} name]]
      [:p.title (get @component-state :title)]
      [:p.composer (get @component-state :composer)]
      ])))

(defn home-page []
  [:div 
    [piece "Counterstream" "http://counterstreamradio.net/" "/api/counterstream"]
    [piece "Drone Zone" "https://somafm.com/dronezone/" "/api/dronezone"]
    [piece "Earwaves" "https://somafm.com/earwaves/" "/api/earwaves"]
    [piece "Q2 Music" "http://q2music.org/" "/api/q2"]
    [piece "Silent Channel" "https://somafm.com/silent/" "/api/silent-channel"]
    [piece "Yle Klassinen" "http://yle.fi/radio/yleklassinen/suora/" "/api/yle"]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
