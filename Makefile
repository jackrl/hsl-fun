guide:
	$(info )
	$(info Options: )
	$(info *    install         - Get dependencies for backend and frontend)
	$(info *    test-backend    - Runs backend tests)
	$(info *    start-backend   - Start backend)
	$(info *    start-frontend  - Start frontend)

install:
	cd backend; lein deps
	cd frontend; npm install

test-backend:
	cd backend; lein test

start-backend:
	cd backend; lein run

start-frontend:
	cd frontend; npm install; npm start
	