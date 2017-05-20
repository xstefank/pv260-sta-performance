
# Task 3 - problem solving

## 1. Problem
### Root causes
1. QA disinterest in actual quality of the product
1. Developers prioritisations - fixing bugs vs new features
1. QA not taking issues seriously - every issue fix should be tested (regressions)

### Possible solutions
1. Involvement of QA - they are not doing their jobs :)
    * QA disinterest and laziness
    * Overall develepment process issues
1. Refinement of the issues creation process - avoid useless issues
    * Issue tracking system may not support such tooling
    * QA may not be interested in searching for duplicates or creating reproducer/steps to reproduce
1. Redefine the QA priorities - QA participation in more general scope of development
    * QA disinterest as they are not interested by far
    * Developers disinterest in participation

## 2. Problem
### Root causes
1. CI is running all sort of tests including the ones that are not necessary for a change done in the fix / release
1. The acceptance tests may consist of unrelevant time-wasting tests that are not part of the performance requirements or specification
1. Running whole test suite for an emergency fix may not be possible due to the time aspect

### Possible solutions
1. Split the acceptance test suite to the smaller clusters of tests
    * Long tests may be unpossible to split
    * Acceptance test suite may break due to the separation
1. Reduce the time by removing long tasks that are not as important / allow to configure testing specifications
    * Problem stating what exactly should be configurable
    * Easy and transparent configuration may require additional effort
1. Emergency fixes should have a separate CI to be always available in such cases
    * No enough resources for another integration framework
    * May be a resource wasting depending of how often emergency fixes happen

## 3. Problem
### Root causes
1. Other teams do not care about test passing
1. There is no code review in those teams
1. Other teams not finding their own errors with enough time left before release

### Possible solutions
1. Pressure other teams not to commit unstable changes
    * Refusal of the participation from the other teams
    * No tooling or knowledge in other teams how to avoid such situations
1. Mandatory code review before a feature gets passed to release branches
    * This might result in work priority change
    * The development will take more time
1. Enhance communications between teams to find problems asap
    * Refusal of participations from both sides
    * Time and priorities management prior to the release

## 4. Problem
### Root causes
1. Unskilled engineers
1. Bad specification of work
1. They need a better Team leader

### Possible solutions
1. Pay them for courses to get some experience
    * Will be time-consuming and expensive
    * If there is a problem with their Attitude, this could not help
1. Give them a better specification
    * Might result into a misunderstanding
    * If this was the problem, than you should consider talking to the QA team lead
1. Speak with the team leader or seek for a new one
    * He can't motivate the engineers to do the work correctly, he is not assertive enough to be a team leader
    * Replacing a team leader is time-consuming. The new one would need time to sink in

## 5. Problem
### Root causes
1. There is no issue prioritization
1. Bad team composition
1. Missing overall awareness about client's needs

### Possible solutions
1. Use Issue-tracking system that allows for issues to set the priority
    * If they're stubborn, there is a chance that they will not follow the highest priority task anyways
    * Getting used to a new tracking system takes some time
1. Switch team composition by changing engineers with other QA or motivate/stimulate some of them to prioritize customer specified issues by involving them in the customer/company communication process
    * Changing the composition could result in new set of problems
    * Involving an engineer in the communication process could not be always a wise choice. But when it goes right, the problem could be solved very fast and effectively
1. Do a meetup regularly to talk about recent issues and assign them as the meetup goes on
    * Again, stubborn engineers might argue
    * (TODO: What could go wrong here?)

## 6. Problem
### Root causes
1. He probably wishes to be promoted
1. He has no other work to do
1. No other person is doing code reviews

### Possible solutions
1. Cool him down at the meeting (whole-team meeting on the last-week completed tasks list - bring up the problem and talk)
    * He could get insecure/angry after receiving a lot of critic
    * May result in a situation when the team leader would have to do the code reviews as a sacrifice.
1. Assign more tasks to him
    * He could argue he gets done twice as much work as others
    * He could begin to be less effective
1. Allow him to do the code reviews, but revise these reviews to be not jumping far behind the task specification
    * If these reviews are really that detailed, this might begin to be too uncomfortable for others and then disallowing him to do the reviews would not be adequate
    * Others will get stressed 

## 7. Problem
### Root causes
1. Small amount of branch levels
1. No testing done before pushing changes
1. Too many people are working with the same branch

### Possible solutions
1. Introduce more levels of development branches
    * Could result in breaking up the current flow
    * More people would be needed to take care of their assigned branches
1. Introduce regression testing and unit testing 
    * Development would be slower
    * Developers will have to implement the tests
1. Assign the merging work to one person per team
    * He could get overwhelmed by just doing those merges
    * Conflict-resolving would be very hard if the person doesn't have enough awareness in his team

## 8. Problem
### Root causes
1.
1.
1.

### Possible solutions
1.
    *
    *
1.
    *
    *
1.
    *
    *

## 9. Problem
### Root causes
1.
1.
1.

### Possible solutions
1.
    *
    *
1.
    *
    *
1.
    *
    *

## 10. Problem
### Root causes
1.
1.
1.

### Possible solutions
1.
    *
    *
1.
    *
    *
1.
    *
    *

## 11. Problem
### Root causes
1.
1.
1.

### Possible solutions
1.
    *
    *
1.
    *
    *
1.
    *
    *

## 12. Problem
### Root causes
1.
1.
1.

### Possible solutions
1.
    *
    *
1.
    *
    *
1.
    *
    *

## 13. Problem
### Root causes
1.
1.
1.

### Possible solutions
1.
    *
    *
1.
    *
    *
1.
    *
    *


