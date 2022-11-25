public enum OperationType {
    ADDITION {
        @Override
        public String toString() {
            return "ADDITION";
        }
    },
    SUBTRACTION {
        @Override
        public String toString() {
            return "SUBTRACTION";
        }
    },
    MULTIPLICATION {
        @Override
        public String toString() {
            return "MULTIPLICATION";
        }
    },
    DIVISION {
        @Override
        public String toString() {
            return "DIVISION";
        }
    },
    INCORRECT {
        @Override
        public String toString() {
            return "INCORRECT";
        }
    }
}