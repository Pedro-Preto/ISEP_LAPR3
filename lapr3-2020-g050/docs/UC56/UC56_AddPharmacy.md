# UC56 - Add Pharmacy

## 1. Usability engineering

### Brief Format

The administrator starts the creation of a Pharmacy on the system. The system asks for the information regarding the Pharmacy.
The administrator introduces the required data. The system validates and presents the pharmacy data, asking for confirmation.
The administrator confirms. The system registers the pharmacy on the system.

### SSD
![UC1_SSD.svg](UC1_SSD.svg)


### Complete Format

#### Primary Actor
* Administrator


#### Stakeholders and interests
* **Pharmacy:**  intends to be registered in the system.
* **Administrator:** intends to do the registration of Pharmacy in the system, so that the can have access to the system.


#### Preconditions
n/a

#### Postconditions
* The information about the pharmacy is stored in the system.

#### Main success Scenario (or Basic Flow)

1. The administrator starts the creation of a Pharmacy on the system.
2. The system asks for the information regarding the Pharmacy.
3. The administrator introduces the required data.
4. The system validates and presents the pharmacy data, asking for confirmation.
5. The administrator confirms.
6. The system registers the pharmacy on the system.

#### Extensions (or Alternative Flows)

*a. The Administrator cancels the creation.

> The use case ends.

4a. Pharmacy's data inserted is invalid.
>	1. The system informs about missing data
>	2. The system allows the reinsertion of the data (step 3)
>
>	    2a. The Administrator doesn't reinsert the data. The use case ends.

4b. Missing minimum required data.
>	1. The system informs which data is missing.
>	2. The system allows the reinsertion of the data (step 3)
>
>	    2a. The Administrator doesn't reinsert the data. The use case ends.

4c. The system detects that the data (or some subset of the data) entered must be unique and already exists in the system.
>	1. The system alerts the Administrator.
>	2. The system allows the reinsertion of the data (step 3)
>
>	    2a. The Administrator doesn't reinsert the data. The use case ends.*


#### Special Requirements
\-

#### Technology and Data Variations List
\-

#### Frequency of Occurence
Once, there is only one pharmacy on the system.

#### Open Issues
* Is there any more required information to create a pharmacy besides the already defined?
* Is there a need to have a Pharmacy Administrator?


## 2. OO Analysis

### Partial Domain Model Relevant for the UC

![UC1_MD.svg](UC1_MD.svg)


## 3. Design - Use Case Realization

### Rational

| Basic flow | Question: Which class... | Answer | Justification |
|:--------------  |:---------------------- |:----------|:---------------------------- |
|1. The Administrator starts the registration of a Pharmacy in the system.|... interacts with the user| AddPharmacyUI |Pure Fabrication|
| |... commands the UC?| AddPharmacyController |Controller|
|2. The system asks the required data ||||
|3. The Administrator introduces the required data. |... saves introduced data?| Pharmacy, PharmacyAdministrator |IE: They have the required data to do the task|
| |... creates Pharmacy instances?| PharmacyRegister |Creator(rule no. 1) & HC+LC. PharmacyRegister is responsible for managing and creating instances of Pharmacy |
| |... creates PharmacyAdministrator instances?| PharmacyRegister |Creator(rule no. 1) & HC+LC. PharmacyRegister is responsible for managing and creating instances of PharmacyAdministrator |
|4. The system presents the data and asks for confirmation.|... validates Pharmacy data (local validation)|Pharmacy|IE: It has its own data|
| |... validates PharmacyAdministrator data (local validation)|PharmacyAdministrator|IE: It has its own data|
| |... validates Pharmacy data (global validation)|PharmacyRegister|IE: PharmacyRegister has organizations registered|
|5. The Administrator confirms. ||||
|6. The system registers the Pharmacy, reports to the Administrator the success of the operation and sends the password through email.|
| | ... saves the pharmacy created?| PharmacyRegister |IE: through the usage of HC+LC on the Platform |
| |... creates the password|PasswordGeneratorAlgorythm|    |



### Systematization ##

 It follows from the rational that the conceptual classes promoted to software classes are:

 * Platform
 * Client
 * ApplicationEM

Other software classes (i.e. Pure Fabrication) identified:  

 * RegisterClientUI  
 * RegisterClientController
 * ClientRegister


###	Sequence Diagram

![UC1_SD.svg](UC1_SD.svg)



###	Class Diagram

![UC1_CD.svg](UC1_CD.svg)
