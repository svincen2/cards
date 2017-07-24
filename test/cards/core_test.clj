(ns cards.core-test
  (:require [clojure.test :refer :all]
            [cards.core :refer :all])
  (:import [cards.core Card]))

(deftest test-rank?
  (testing "returns the rank if valid"
    (is (= :ace (rank? :ace))))
  (testing "returns nil if rank is invalid"
    (is (= nil (rank? :bad-rank)))))

(deftest test-suit?
  (testing "returns the suit if valid"
    (is (= :clubs (suit? :clubs))))
  (testing "returns nil if the suit is invalid"
    (is (= nil (suit? :bad-suit)))))

(deftest test-make-card
  (testing "fails with bad rank"
    (is (thrown? AssertionError (make-card {:rank :bad-rank :suit :clubs}))))
  (testing "fails with bad suit"
    (is (thrown? AssertionError (make-card {:rank :ace :suit :bad-suit}))))
  (testing "creates a new card"
    (let [card (make-card {:rank :ace :suit :clubs})]
      (is (= (class card) cards.core.Card))))
  (testing "returned card has correct rank and suit"
    (let [card (make-card {:rank :10 :suit :spades})]
      (is (= (:rank card) :10))
      (is (= (:suit card) :spades)))))

