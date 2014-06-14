(ns berlin_clock.core)

(use '[clojure.string :only (join split)])

(defn show [time]
  (let
    [[h m s] (map #(Integer. %) (split time #":"))
     seconds-lamp (fn [s]
                    (if (= 0 (rem s 2))
                      "Y" "R"))]
    (join "\n"
          [(seconds-lamp s)
           "OOOO"
           "OOOO"
           "OOOOOOOOOOO"
           "OOOO"])))
