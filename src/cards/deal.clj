(ns cards.core)

(defn deal
  "Expects a deck of cards.
  Deals one card from the top of the deck.
  Returns a map containing:
  :dealt - The dealt card.
  :deck  - The new deck without the dealt card."
  [deck]
  {:dealt (first deck)
   :deck  (drop 1 deck)})

(defn deal!
  "Expects an atom containing a deck of cards.
  Deals one card from the top of the deck,
  altering the deck in the process."
  [deck]
  (let [dealt (first @deck)]
    (swap! deck (partial drop 1))
    dealt))

(defn deal-n
  "Expects a number n and a deck of cards.
  Deals n cards from the top of the deck.
  Returns a map containing:
  :dealt - the dealt cards
  :deck  - the new deck without the dealt cards."
  [n deck]
  {:dealt (take n deck)
   :deck  (drop n deck)})

(defn deal-n!
  "Expects an atom containing a deck of cards.
  Deals n cards from the top of the deck, altering
  the deck in the process."
  [n deck]
  (let [dealt (take n @deck)]
    (swap! deck (partial drop n))
    dealt))

