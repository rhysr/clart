(ns cart.routes.home
  (:require [compojure.core :refer :all]
            [hiccup.element :only (link-to unordered-list)]
            [cart.views.layout :as layout]
            [cart.models.product.productList :as productList]))



(defn home []
  (layout/common
    [:div {:class "container"}
      [:h1 "Hello World!" ]
      [:ul
      (for
        [product productList]
        [:li (link-to (str "/product/" (product :productId)) (product :name))])]]))

(defroutes home-routes
  (GET "/" [] (home)))
