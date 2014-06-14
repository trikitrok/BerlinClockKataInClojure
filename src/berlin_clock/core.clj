(ns berlin_clock.core)

(use '[clojure.string :only (join split)])

(defn show [time]
  (let
    [[h m s] (map #(Integer. %) (split time #":"))

     show-seconds (fn [s]
                    (if (zero? (rem s 2))
                      "Y" "R"))

     show-hours-first-row (fn [h]
                       (apply str (concat (repeat (quot h 5) "R")(repeat (- 4 (quot h 5)) "O"))))]

    (join "\n"
          [(show-seconds s)
           (show-hours-first-row h)
           "OOOO"
           "OOOOOOOOOOO"
           "OOOO"])))
