(ns berlin_clock.core)

(use '[clojure.string :only (join split)])

(defn- turn-on-red [num-lamps] 
  (repeat num-lamps "R"))

(defn- turn-on-yellow [num-lamps] 
  (repeat num-lamps "Y"))

(defn- turn-off [num-lamps] 
  (repeat num-lamps "O"))

(defn- turn-on-YYR [num-lamps-on] 
  (take num-lamps-on (cycle ["Y" "Y" "R"])))

(defn- show-lamps [num-lamps-on num-lamps turn-on]
  (let [show (comp join concat)
        num-lamps-off (- num-lamps num-lamps-on)]
    (show (turn-on num-lamps-on)
          (turn-off num-lamps-off))))

(defn show [time]
  (let
    [[h m s] (map #(Integer. %) (split time #":"))
     
     seconds-lamps-row (if (zero? (rem s 2)) "Y" "O")
     
     hours-lamps-first-row (show-lamps (quot h 5) 4 turn-on-red)
     
     hours-lamps-second-row (show-lamps (rem h 5) 4 turn-on-red)
     
     minutes-lamps-first-row (show-lamps (quot m 5) 11 turn-on-YYR)
     
     minutes-lamps-second-row (show-lamps (rem m 5) 4 turn-on-yellow)]
    
    (join "\n"
          [seconds-lamps-row
           hours-lamps-first-row
           hours-lamps-second-row
           minutes-lamps-first-row
           minutes-lamps-second-row])))