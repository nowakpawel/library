# MVP Library Fullstack application
## Stack:
### Backend
- Postgresql
- Docker
- Flyway
- Java 17
- JPA
- Hibernate
- JUnit
### Frontend
- HTML
- React
- CSS

## To Do:
### Backend:
- [x] Book Entity
- [x] JPA Book Repository
- [x] Book Service (CRUD)
- [x] Book Controller
- [x] BookServiceTest
- [x] BookControllerTest
- [ ] [JPARepository] Instead of updating entire book - update only it's status.
- [ ] [BookService] Throw exception instead of returning null.
- [ ] [Book Entity] Add Book status and Book summary
- [ ] ModelMapper - Entity/DTO
- [ ] Add swagger

### Frontend:
- [ ] Main Page with all books slider
- [ ] Details page with all book details, description and Button for lend
- [ ] [Frontend/Backend] When 'Lend a book' button is clicked Status is changed.
- [ ] [Frontent/Backend] Book image
- [ ] Pagination

### Future Features:
- [ ] Login using oauth
- [ ] Lend book button available only for Administrator
