(ns cards.core-render-test
  (:require [clojure.test :refer :all]
            [cards.core :refer :all]))

(deftest test-suit->char
  (testing "returns correct char when used as a function"
    (is (= \u2663 (suit->char :clubs)))
    (is (= \u2666 (suit->char :diamonds)))
    (is (= \u2665 (suit->char :hearts)))
    (is (= \u2660 (suit->char :spades)))))

(deftest test-suit->color
  (testing "returns correct color when used as a function"
    (is (= :blue (suit->color :clubs)))
    (is (= :magenta (suit->color :diamonds)))
    (is (= :red (suit->color :hearts)))
    (is (= :black (suit->color :spades)))))

(deftest test-rank->str
  (testing "returns first char capitolized for ace, king, queen, and jack"
    (is (= "A" (rank->str :ace)))
    (is (= "K" (rank->str :king)))
    (is (= "Q" (rank->str :queen)))
    (is (= "J" (rank->str :jack))))
  (testing "returns (name rank) for all other ranks"
    (let [remaining (filter #(not (#{:ace :king :queen :jack} %)) ranks)]
      (doall
        (for [rank remaining]
          (is (= (name rank) (rank->str rank))))))))

(deftest test-card->str
  (testing "returns (str (rank->str rank) \\space (suit->char suit))"
    (is (= (str "A " \u2663)
           (card->str (make-card {:rank :ace :suit :clubs}))))
    (is (= (str "K " \u2666)
           (card->str (make-card {:rank :king :suit :diamonds}))))
    (is (= (str "10 " \u2665)
           (card->str (make-card {:rank :10 :suit :hearts}))))
    (is (= (str "2 " \u2660)
           (card->str (make-card {:rank :2 :suit :spades}))))))

