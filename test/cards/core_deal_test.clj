(ns cards.core-deal-test
  (:require [cards.core :refer :all]
            [clojure.test :refer :all]))

(deftest test-deal
  (testing "returns a map with :dealt and :deck keys"
    (let [res (deal new-deck)]
      (is (:dealt res))
      (is (:deck res))))
  (testing "deals a single card"
    (let [{:keys [dealt _]} (deal new-deck)]
      (is (= cards.core.Card (class dealt)))))
  (testing "deals the top card of the deck"
    (let [{:keys [dealt _]} (deal new-deck)]
      (is (= dealt (first new-deck)))))
  (testing "new deck has top card missing"
    (let [{:keys [_ deck]} (deal new-deck)]
      (is (= (drop 1 new-deck) deck)))))

(deftest test-deal!
  (testing "returns a single card"
    (let [deck (atom new-deck)
          dealt (deal! deck)]
      (is (= cards.core.Card (class dealt)))))
  (testing "removes the dealt card from the deck"
    (let [deck (atom new-deck)
          dealt (deal! deck)]
      (is (= (count @deck) (- (count new-deck) 1)))
      (is (empty? (filter #(= dealt %) @deck)))))
  (testing "deals the top card from the deck"
    (let [deck (atom new-deck)
          dealt (deal! deck)]
      (is (= (first new-deck) dealt)))))

(deftest test-deal-n
  (testing "returns a map with :dealt and :deck keys"
    (let [res (deal-n 5 new-deck)]
      (is (:dealt res))
      (is (:deck res))))
  (testing "deals correct amount of cards"
    (let [{dealt :dealt} (deal-n 5 new-deck)]
      (is (= 5 (count dealt)))))
  (testing "returns a new deck w/ n less cards"
    (let [start (count new-deck)
          {deck :deck} (deal-n 5 new-deck)]
      (is (= (count deck) (- start 5)))))
  (testing "returns a new deck w/o the dealt cards"
    (let [{:keys [dealt deck]} (deal-n 5 new-deck)]
      (doall
        (for [card dealt]
          (is (empty? (filter #(= card %) deck))))))))

(deftest test-deal-n!
  (testing "returns a seq of n cards"
    (let [deck (atom new-deck)
          dealt (deal-n! 5 deck)]
      (is (seq? dealt))
      (is (= 5 (count dealt)))))
  (testing "removes n cards from the top of the deck"
    (let [deck (atom new-deck)
          dealt (deal-n! 10 deck)]
      (is (= (count @deck) (- (count new-deck) 10)))
      (is (= dealt (take 10 new-deck))))))

