# Rewards Points Sample
 
In memory H2 database is used that generates random data each time app starts.

Two customers, 400 transactions, randomly generated amounts, dates, type, over past 90 days.

Rewards amount is configurable via live sql database. 

Endpoints
```
/rewards/total-previous-days/{days}?customerId={id}
/transaction/previous-days/{days}?customerId={id}
/customer/{id}
```