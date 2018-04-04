# datascript-schema

[insert explosive graphic]

[insert wise quote]

## Usage

A simple utility to replicate what datomic schema has

Preparing

```clojure 
(ns my-project.core 
  (:require 
   [datascript.core :as d]
   [datascript-schema.core :as ds]))

(def conn (d/create-conn))

(ds/bootstrap! conn)

(defn current-schema
  []
  (:schema @conn))

```

Actually trying it

```clojure

;; Adding new schema
user> (d/transact conn [{:db/id -1
                         :db/ident :role/members
                         :db/valueType [:db/ident :db.type/ref]
                         :db/cardinality [:db/ident :db.cardinality/many]}])

user> (current-schema)
;; {:db/ident {:db/unique :db.unique/identity},
;;  :db/valueType {:db/valueType :db.type/ref},
;;  :db/unique {:db/valueType :db.type/ref},
;;  :db/cardinality {:db/valueType :db.type/ref},
;;  :role/members {:db/cardinality :db.cardinality/many,
;;                 :db/valueType :db.type/ref}}

;; Updating schema
(d/transact conn [{:db/id -1
                   :db/ident :role/members
                   :db/cardinality [:db/ident :db.cardinality/one]}])

user> (current-schema)
;; {:db/ident {:db/unique :db.unique/identity},
;;  :db/valueType {:db/valueType :db.type/ref},
;;  :db/unique {:db/valueType :db.type/ref},
;;  :db/cardinality {:db/valueType :db.type/ref},
;;  :role/members {:db/cardinality :db.cardinality/one, :db/valueType :db.type/ref}}

;; Retracting a part of schema
user> (d/transact conn [[:db/retract
                         [:db/ident :role/members]
                         :db/valueType
                         [:db/ident :db.type/ref]]])

user> (current-schema)
;; {:db/ident {:db/unique :db.unique/identity},
;;  :db/valueType {:db/valueType :db.type/ref},
;;  :db/unique {:db/valueType :db.type/ref},
;;  :db/cardinality {:db/valueType :db.type/ref},
;;  :role/members {:db/cardinality :db.cardinality/one}}

;; Completely retract a schema
user> (d/transact conn [[:db.fn/retractEntity [:db/ident :role/members]]])

user> (current-schema)
;; {:db/ident {:db/unique :db.unique/identity},
;;  :db/valueType {:db/valueType :db.type/ref},
;;  :db/unique {:db/valueType :db.type/ref},
;;  :db/cardinality {:db/valueType :db.type/ref}}


```

## License

Copyright © 2018 fmnasution

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
