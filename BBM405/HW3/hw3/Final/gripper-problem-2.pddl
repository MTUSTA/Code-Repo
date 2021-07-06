(define (problem gripper-2) 
(:domain gripper-domain) 
(:objects rooma roomb roomc ball1 ball2 ball3 left right up down) 
(:init 
	(room rooma) 
	(room roomb)
	(room roomc)
	(ball ball1) 
	(ball ball2)
	(ball ball3)
	(gripper left) 
	(gripper right)
	(gripper up)
	(gripper down)
	(at-robby rooma) 
	(free left) 
	(free right) 
	(free up) 
	(free down) 
	(at ball1 rooma) 
	(at ball2 roomb)
	(at ball3 roomc)
)
(:goal (and(at ball1 rooma) (at ball2 rooma) (at ball3 roomb) )))