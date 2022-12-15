# UC5 - Mark Order as Received

## 1. Usability engineering

### Brief Format

	The Client opens the list of his orders, the System shows him his orders, the Client selects an Order from the list, the System shows all the details of the Order and if the Order is marked as delivered, but not received, the option to mark the order as received also apears, the Client marks the Order as received, the System asks for confirmation, the Client confirms, the System notifies the Client that the state of his Order has been altered.

### SSD
![UC5_SSD.svg](UC5_SSD.svg)


### Complete Format

#### Primary Actor
* Client

#### Stakeholders and interests
* **Client:** intends to confirm that a delivered Order has been received.
* **Pharmacy:** intends to have a Courier correctly deliver the Orders.
* **Courier:** intends to correctly deliver the Orders.
* **System:** intends to update the state of the Orders.



#### Preconditions
* The Client has to have done orders.
* The Order selected by the Client has to have its state as delivered.

#### Postconditions
* The state of the Order has to change to complete
* The information about the Order has to be updated.

#### Main success Scenario (or Basic Flow)

1. The Client opens the list of his orders.
2. The System shows him his orders.
3. The Client selects an Order from the list.
4. The System shows all the details of the Order.
5. The Client marks the Order as received.
6. The System asks for confirmation.
7. The Client confirms.
8. The System notifies the Client that the state of his Order has been altered.

#### Extensions (or Alternative Flows)

2a. The Client exits the list of his Orders.

> The use case ends.

4a. The Client exits the details of the selected Order.
>	1. The user can go back to the list of his Orders
>	2. The system allows the selection of an Order (step 3)

4b. The selected Order isn't marked as delivered.
>	1. The user can go back to the list of his Orders
>	2. The system allows the selection of an Order (step 3)

6a. The Client doesn't confirm.
>	1. The system shows the details of the selected Order (step 4).

#### Special Requirements
\-

#### Technology and Data Variations List
\-

#### Frequency of Occurence
Whenever the Client wants to mark a delivered Order as received.

#### Open Issues
* Are there any more requirements to consider that a Client has received an Order?


## 2. OO Analysis

### Partial Domain Model Relevant for the UC

![UC5_MD.svg](UC5_MD.svg)


## 3. Design - Use Case Realization

### Rational __F A L T A    I S T O__

| Basic flow | Question: Which class... | Answer | Justification |
|:--------------  |:---------------------- |:----------|:---------------------------- |
|||||
|||||
|||||
|||||
|||||
|||||
|||||
|||||
|||||


### Systematization ##

 It follows from the rational that the conceptual classes promoted to software classes are:

 * User
 * System
 * Client
 * Order
 * Delivery

Other software classes (i.e. Pure Fabrication) identified:  

 * ConfirmDeliveryUI  
 * ConfirmDeliveryController
 * OrderDatabase


###	Sequence Diagram

![UC5_SD.svg](UC5_SD.svg)



###	Class Diagram

![UC5_CD.svg](UC5_CD.svg)
