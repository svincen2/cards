(ns cards.core)

; TODO - Jokers

(def ranks
  #{:ace :2 :3 :4 :5 :6 :7 :8 :9 :10 :jack :queen :king})

(def suits
  #{:clubs :diamonds :hearts :spades})

(defn rank?
  "Tests whether the given argument is a valid rank."
  [rank]
  (ranks rank))

(defn suit?
  "Test whether the given argument is a valid suit."
  [suit]
  (suits suit))

(defrecord Card [rank suit])

(defn make-card
  "Expects a map with :rank and :suit keys.
  Constructs a new card with the given rank and suit."
  [{:keys [rank suit] :as props}]
  {:pre [(rank? rank)
         (suit? suit)]}
  (map->Card props))

(def new-deck
  (flatten
    (for [suit suits]
      (for [rank ranks]
        (make-card {:rank rank :suit suit})))))

; Load in 'sub-namespaces'
(load "deal"
      "render")

