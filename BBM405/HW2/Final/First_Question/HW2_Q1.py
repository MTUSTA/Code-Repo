from z3 import *

# 3-SAT tries to find whether there are such values of A-F that make the Boolean expression TRUE.
A, B, C, D, E, F = Bools('A B C D E F')

# 1. -> (¬A v B v C) ∧ (B v ¬C v D) ∧ (D v E v ¬F)
print("------------------------------------------")
s = Solver()
s.add(And(Or(Not(A), B, C), Or(B, Not(C), D), Or(D, E, Not(F))))
print("Solver to SMT")
print(s.to_smt2())

if s.check() == z3.sat:
    # result
    print(s.model())


# 2. -> (¬A v ¬B v C) ∧ (B v ¬C v ¬D) ∧ (¬D v E v ¬F)
print("------------------------------------------")
s = Solver()
s.add(And(Or(Not(A), Not(B), C), Or(B, Not(C), Not(D)), Or(Not(D), E, Not(F))))
print("Solver to SMT")
print(s.to_smt2())
if s.check() == z3.sat:
    # result
    print(s.model())


# 3. -> (A v ¬B v C) ∧ (¬B v C v ¬D) ∧ (D v ¬E v ¬F)
print("------------------------------------------")
s = Solver()
s.add(And(Or(A, Not(B), C), Or(Not(B), C, Not(D)), Or(D, Not(E), Not(F))))
print("Solver to SMT")
print(s.to_smt2())
if s.check() == z3.sat:
    # result
    print(s.model())



# 4. -> (A v ¬F v C) ∧ (¬E v A v ¬D) ∧ (D v ¬C v ¬A)
print("------------------------------------------")
s = Solver()
s.add(And(Or(A, Not(F), C), Or(Not(E), A, Not(D)), Or(D, Not(C), Not(A))))
print("Solver to SMT")
print(s.to_smt2())
if s.check() == z3.sat:
    # result
    print(s.model())



# 5. -> (F v D v ¬A) ∧ (B v F v E) ∧ (¬C v E v ¬B)
print("------------------------------------------")
s = Solver()
s.add(And(Or(F, D, Not(A)), Or(B, F, E), Or(Not(C), E, Not(B))))
print("Solver to SMT")
print(s.to_smt2())
if s.check() == z3.sat:
    # result
    print(s.model())