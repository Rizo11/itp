public enum CalculatorType {
    INTEGER {
        @Override
        public String toString() {
            return "INTEGER";
        }
    },
    DOUBLE {
        @Override
        public String toString() {
            return "DOUBLE";
        }
    },
    STRING {
        @Override
        public String toString() {
            return "STRING";
        }
    },
    INCORRECT {
        @Override
        public String toString() {
            return "INCORRECT";
        }
    }
}