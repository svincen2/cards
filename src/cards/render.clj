(ns cards.core
  (:require [clojure.string :as str]
            [terminal.ansi :refer [ansi-str]]))

(def suit->char
  {:clubs \u2663
   :diamonds \u2666
   :hearts \u2665
   :spades \u2660})

(def suit->color
  {:clubs :blue
   :diamonds :magenta
   :hearts :red
   :spades :black})

(defn rank->str
  "Returns the string representation of a rank."
  [rank]
  (or ({:ace "A"
        :king "K"
        :queen "Q"
        :jack "J"} rank)
      (name rank)))

(defn card->str
  "Returns the string representation of a card."
  [{:keys [rank suit]}]
  (str (rank->str rank) \space (suit->char suit)))

(defn card->ansi-str
  "Returns an ansi string representation of a card.
  Accepts the same options as terminal.ansi/ansi-str.
  Default ansi options are to use the predefined color
  for the card's suit."
  ([card]
   (card->ansi-str card (suit->color (:suit card))))
  ([card & ansi-opts]
   (let [card-str (card->str card)]
     (apply ansi-str card-str ansi-opts))))

(defn cards->str
  "Returns the string representation of the given cards.
  Renders each card using card->str, then joins then with
  the given separator (defaults to \newline)."
  ([cards]
   (cards->str \newline))
  ([cards sep]
   (str/join sep (map card->str cards))))

(defn cards->ansi-str
  "Returns the ansi string representation of the given cards.
  Accepts the same options as terminal.ansi/ansi-str.
  If specified, ansi options apply to all cards.
  If unspecified, acts as if calling card->ansi-str on each
  card without specified ansi options (i.e. each card will be
  the color of that card's suit)."
  ([cards]
   (cards->ansi-str cards \newline))
  ([cards sep]
   (str/join sep (map card->ansi-str cards)))
  ([cards sep & ansi-opts]
   (str/join sep (map #(apply card->ansi-str % ansi-opts) cards))))

