THE WORKING BRANCH IS THE MONGODB BRANCH, THE OTHER IS THE START OF THE IMPLEMENTATION OF A SQL BASED BACKEND


Documentation for the Project

Definition

In the definition phase the concrete planes for the Software that should be implemented.

The goal of the project is to implement a supermarket register with a frontend, backend, and database.
The frontend is going to be based on a vite started React application. 
The UI should show 4 different displays and including a page to create items that can be saved in the Database, 
a page to print labels for a specific item, 
a page that shows a shopping cart that shows a list of all the slected items and a page that shows the receipt and enables the printing of the receipt.
The backend is a SpringBoot Application with Java that provides a library that enables the printing of barcodes and receipts and other components. This enables the possibility to create custom printed labels and receipts.
The backend includes REST controllers and entitys as well as repositories to enable the communication with the database, which in this case is MongoDB.
MongoDB as the database provides the possibility to store datapoints as files.

The requirments from the customer are the following:
1. The technical setup includes a printer for the receipts and labels and a scanner to scann the labels.
2. The project should be finished until the 04.11.2024
3. There are no costs and ressources allocated

A user story map in included with a picture.
There are no non-functional requirments regarding performance, security or else.
Functional requirments are the scanning of items, the printing of a receipt, the ///////////////////////////////////////////////////////

Further graphs and design mock ups are not required.

All the data gets transfered within JSON Objects which provide a wide range of acceptance and felxibility within on and many different systems. The data gets stored in MongoDB as a file, but can be extracted as a JSON as it is placed in the syntax of a JSON.
Data protection is not a requirment, therefore there is no implementation of security and data safety features.

Two porcesses can be seen in sequence diagramms which are porvided in two picutres.

There are no constrains except unforeseen events such as a power outage or galactic radiation, and, of course, our own inability to produce good code.

Acceptance criteria as well as tests are not required therefore they are neglected.

A project roadmap exists in the form of github commits which can trace back to the creation of the project, however a predefined roadmap was not a requirment, therefore, once more, there is no roadmap.

Thank you and enjoy the MEMES!!!