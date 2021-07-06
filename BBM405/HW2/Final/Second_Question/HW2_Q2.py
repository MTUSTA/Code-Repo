from z3 import *

# 3-SAT tries to find whether there are such values of A-F that make the Boolean expression TRUE.
A, B, C, D, E, F = Bools('A B C D E F')

s = Solver()

# 1. (¬A <-> B -> C) ∧ (B -> ¬C <-> D) ∧ (D <-> E <-> ¬F)
s.add(
    And(
        # (¬A <-> B -> C)
        And(Implies(Not(A), Implies(B, C)), Implies(Implies(B, C), Not(A))),
        # (B -> ¬C <-> D)
        And(Implies(Implies(B, Not(C)), D), Implies(D, Implies(B, Not(C)))),
        # (D <-> E <-> ¬F)
        And(Implies(And(Implies(D, E), Implies(E, D)), Not(F)), Implies(Not(F), And(Implies(D, E), Implies(E, D))))
    )
)

print("Solver to SMT")
print(s.to_smt2())
if s.check() == z3.sat:
    # result
    print(s.model())

print("----------------------------------------------------")

s = Solver()
# 2. (¬A <-> B v C) ∧ (B -> ¬C -> D) ∧ (D v ¬E <-> ¬F)
s.add(
    And(
        # (¬A <-> B v C)
        And(Implies(Not(A), Or(B, C)), Implies(Or(B, C), Not(A))),
        # (B -> ¬C -> D)
        Implies(Implies(B, Not(C)), D),
        # (D v ¬E <-> ¬F)
        And(Implies(Or(D, Not(E)), Not(F)), Implies(Not(F), Or(D, Not(E))))
    )
)
print("Solver to SMT")
print(s.to_smt2())
if s.check() == z3.sat:
    # result
    print(s.model())

print("----------------------------------------------------")

s = Solver()
# 3. (A v ¬B v C) ∧ (B <-> ¬C <-> D) ∧ (¬D <-> E -> ¬F)
s.add(
    And(
        # (A v ¬B v C)
        Or(A, Not(B), C),
        # (B <-> ¬C <-> D)
        And(Implies(And(Implies(B, Not(C)), Implies(Not(C), B)), D),
            Implies(D, And(Implies(B, Not(C)), Implies(Not(C), B)))),
        # (¬D <-> E -> ¬F)
        And(Implies(Not(D), Implies(E, Not(F))), Implies(Implies(E, Not(F)), Not(D)))
    )

)
print("Solver to SMT")
print(s.to_smt2())
if s.check() == z3.sat:
    # result
    print(s.model())

print("----------------------------------------------------")

s = Solver()
# 4. (A -> ¬B v C) ∧ (F v ¬A -> D) ∧ (¬D -> E <-> A)
s.add(
    And(
        # (A -> ¬B v C)
        Implies(A, Or(Not(B), C)),
        # (F v ¬A -> D)
        Implies(Or(F, Not(A)), D),
        # (¬D -> E <-> A)
        And(Implies(Implies(Not(D), E), A), Implies(A, Implies(Not(D), E)))
    )

)
print("Solver to SMT")
print(s.to_smt2())
if s.check() == z3.sat:
    # result
    print(s.model())

print("----------------------------------------------------")

s = Solver()
# 5. (¬E v B -> C) ∧ (A v ¬C <-> D) ∧ (¬D <-> ¬E v F)
s.add(
    And(
        # (¬E v B -> C)
        Implies(Or(Not(E), B), C),
        # (A v ¬C <-> D)
        And(Implies(Or(A, Not(C)), D), Implies(D, Or(A, Not(C)))),
        # (¬D <-> ¬E v F)
        And(Implies(Not(D), Or(Not(E), F)), Implies(Or(Not(E), F), Not(D)))

    )
)
print("Solver to SMT")
print(s.to_smt2())
if s.check() == z3.sat:
    # result
    print(s.model())

print("----------------------------------------------------")
