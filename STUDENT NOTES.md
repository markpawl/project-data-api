# Student workshop notes

## Introduction

To support you in workshops, partially-complete code samples are provided for most of the development exercises that you will do as part of the workshop.  These samples are from a complete, working implementation, with much of the interesting code removed so that you can have the pleasure of developing your own implementation.

Do not feel that you need to follow the sample code; it is provided as an optional guide, but if you can, don't use the sample code.

A few notes about the sample code:

* Imports have not been updated, so they are whatever the original implementation called for.  You may (probably will) find it neccessary to add your own imports, and you may wish to clean out imports that are not being used in your implementation.
* For the most part, just the implementation of methods has been removed as an exercise for you.  Do not feel that you have to use the remaining code as-is; if you feel you want to change the argument list to a function, do so; just be careful that you update references in other parts of the project.
* Hints and instructions are in comments, starting with "workshop:"

## Implementation of APIs (com.webage.api.*)

The com.webage.api package contains implementations of APIs that manipulate entities such as Customer and Event.  

The API for each of these entities follows a very similar pattern; there are methods for creating, retrieving, changing, and deleting instances of each entity.  Both the interface and the technical implementation are similar across entities.

We suggest that you implement, more or less completely, a single entity before moving on to the others.  It isn't required that you do this, but it's often good practice; you get to fully flesh out an API implementation and shake out those things you didn't think of up front, so that when you move on to the next entity you have a really clear idea of how to do it well.  You also tend to get more consistency across APIs, which makes it easier for consumers to work with them.

Remember, this is just a suggestion.  Sometimes you can't do it this way, because you need several APIs to be partially implemented to be able to test some other function.  In this case, you can minimize the amount of effort you are committing to and minimize the amount of refactoring you may have to do later to get your API implementations consistent.
