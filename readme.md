# Employee Tracking System


## Features
1. **Department Management**
    - View, add, update, and delete departments.
    - Retrieve projects by department.

2. **Employee Management**
    - View, add, update, and delete employees.
    - Search employees by name, email, or department.

3. **Project Management**
    - View, add, update, and delete projects.

4. **Role-Based Access Control (RBAC)**
    - Restrict API access based on user roles (EMPLOYEE,MANAGER,ADMIN).

---

## API Endpoints

### **Department APIs**
The Department APIs provide functionality to manage department-related data. The following operations are supported:

GET /departments: Retrieves a list of all departments. Accessible by roles: ADMIN and MANAGER.
GET /departments/{id}: Fetches details of a specific department by its ID. Accessible by roles: ADMIN and MANAGER.
POST /departments: Allows adding a new department. Accessible by role: ADMIN.
PUT /departments/{id}: Updates details of an existing department. Accessible by role: ADMIN.
DELETE /departments/{id}: Deletes a specific department. Accessible by role: ADMIN.
GET /departments/{id}/projects: Fetches projects within a specific department. Accessible by roles: ADMIN and MANAGER.

---

### **Employee APIs**
The Employee APIs enable operations on employee data. The supported endpoints are:

GET /employees: Retrieves a list of all employees. Accessible by roles: ADMIN and MANAGER.
GET /employees/{id}: Fetches details of a specific employee by ID. Accessible by ADMIN and MANAGER (with certain restrictions for managers).
POST /employees: Allows adding a new employee. Accessible by role: ADMIN.
PUT /employees/{id}: Updates details of an existing employee. Accessible by role: ADMIN.
DELETE /employees/{id}: Deletes a specific employee. Accessible by role: ADMIN.
GET /employees/search: Allows searching for employees based on certain criteria. Accessible by roles: ADMIN and MANAGER.
---

### **Project APIs**
The Project APIs provide functionality for managing projects. The endpoints include:

GET /projects: Retrieves a list of all projects. Accessible by roles: ADMIN and MANAGER.
GET /projects/{id}: Fetches details of a specific project by its ID. Accessible by roles: ADMIN and MANAGER.
POST /projects: Allows adding a new project. Accessible by role: ADMIN.
PUT /projects/{id}: Updates details of an existing project. Accessible by role: ADMIN.
DELETE /projects/{id}: Deletes a specific project. Accessible by role: ADMIN.
---

## Role-Based Access Control
- **ADMIN**: Full access to all APIs.
- **MANAGER**: Limited access based on department and employee restrictions.
- **EMPLOYEE**: Limited access to own employee details.

---
