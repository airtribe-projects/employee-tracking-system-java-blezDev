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

## **Department APIs**

The Department APIs provide functionality to manage department-related data. The following operations are supported:

- **GET /departments**  
  Retrieves a list of all departments.  
  **Accessible By:** Roles: `ADMIN`, `MANAGER`

- **GET /departments/{id}**  
  Fetches details of a specific department by its ID.  
  **Accessible By:** Roles: `ADMIN`, `MANAGER`

- **POST /departments**  
  Allows adding a new department.  
  **Accessible By:** Role: `ADMIN`

- **PUT /departments/{id}**  
  Updates details of an existing department.  
  **Accessible By:** Role: `ADMIN`

- **DELETE /departments/{id}**  
  Deletes a specific department.  
  **Accessible By:** Role: `ADMIN`

- **GET /departments/{id}/projects**  
  Fetches projects within a specific department.  
  **Accessible By:** Roles: `ADMIN`, `MANAGER`

---

## **Employee APIs**

The Employee APIs enable operations on employee data. The following endpoints are supported:

- **GET /employees**  
  Retrieves a list of all employees.  
  **Accessible By:** Roles: `ADMIN`, `MANAGER`

- **GET /employees/{id}**  
  Fetches details of a specific employee by ID.  
  **Accessible By:** Roles: `ADMIN`, `MANAGER` (with certain restrictions for `MANAGER`)

- **POST /employees**  
  Allows adding a new employee.  
  **Accessible By:** Role: `ADMIN`

- **PUT /employees/{id}**  
  Updates details of an existing employee.  
  **Accessible By:** Role: `ADMIN`

- **DELETE /employees/{id}**  
  Deletes a specific employee.  
  **Accessible By:** Role: `ADMIN`

- **GET /employees/search**  
  Allows searching for employees based on certain criteria.  
  **Accessible By:** Roles: `ADMIN`, `MANAGER`

---

## **Project APIs**

The Project APIs provide functionality for managing projects. The following operations are supported:

- **GET /projects**  
  Retrieves a list of all projects.  
  **Accessible By:** Roles: `ADMIN`, `MANAGER`

- **GET /projects/{id}**  
  Fetches details of a specific project by its ID.  
  **Accessible By:** Roles: `ADMIN`, `MANAGER`

- **POST /projects**  
  Allows adding a new project.  
  **Accessible By:** Role: `ADMIN`

- **PUT /projects/{id}**  
  Updates details of an existing project.  
  **Accessible By:** Role: `ADMIN`

- **DELETE /projects/{id}**  
  Deletes a specific project.  
  **Accessible By:** Role: `ADMIN`

---

## Notes

- Replace `{id}` in the URL with the specific resource ID (e.g., department, employee, or project).
- Ensure proper role-based access control is implemented before using these endpoints.

## Role-Based Access Control
- **ADMIN**: Full access to all APIs.
- **MANAGER**: Limited access based on department and employee restrictions.
- **EMPLOYEE**: Limited access to own employee details.

---
