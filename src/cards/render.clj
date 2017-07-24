(ns cards.core)

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

