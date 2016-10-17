(ns now-playing-web.core
    (:require [reagent.core :as reagent]))

(def api-url "http://now-playing-api.herokuapp.com")
;; -------------------------
;; Views

(defn piece [name url source]
  [:div 
  [:h2 [:a {:href url :target "_blank"} name]]
  [:p.title "Title"]
  [:p.composer "Composer"]])

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
