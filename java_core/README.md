# java

- 

## Strings
- StringBuilder is thread safe and mutable
  - StringBuilder sb = new StringBuilder("test sb");
  - .append("abc")
  - .reverse()
  - .toString()
- ALWAYS USE .equals(str) to compare strings


## functions
- in function overloading, if there is no exact match and there are two methods that are at the same level when upcasted, error is thrown

## encapsulation
- reusability of code
- easy maintenance
- easy to address complexity


# Optional modifiers

- constructor can not be marked with optional modifiers


### static
- when jvm encounters a object which is not loaded, it invokes the class loader
    - class loader reader the byte code and loads it to the heap as a class class object
    - class loading happens once per class per program execution
    - class class objects are never garbage collected and JVM maintains reference to them
    - static members reside in class class object
    - class loading happens for the class first time it is used
    - it is destroyed when the program ends and heap memory is returned to the os
- can be used with: -
    - method 
        - class scoped method
        - non behavioral method 
        - Best Practice: always call static methods using class name and never use reference variable
        - used for utility functionality (methods that are only dependent on input and not state of object)
        - static methods cant access non static members without creating object
    - variable 
        - belongs to the class and only one copy per class
        - stored in class class object
        - initialized like instance variables (local variables are not initialized )
    - initializer- there are 3 types of initializer
        - field initializer- instance variables initialized inside class
            - int x = 20; //executed when the object is created
            - static int a = 10; //executed when the class class object is loaded
            - static double d = Math.random(); 
        - instance initializer-
            - {} code block coded inside a class outside other members
            - can access instance members and static members
        - static initializer
    - inner class
- 


### final modifier
- a final class can not be extended
- a final method can not be overridden
- a final variable's value can not be changed




## order of execution for object creation
- static field initializers- executed first when the class is loaded for static variables
    - static int a = 10; //executed when the class class object is loaded
    - static double d = Math.random(); 
- static initializers- executed when the class is loaded
    - static {a=5;}
    - executed once per class
- field initializers- executed when the object is created
    - int x = 20; 
- instance initializers (can be more than one - executed in order): when we want some lines of code to be executed no matter which constructor is executed we use instance initializer
    - {} code block coded inside a class outside other members
    - can access instance members and static members
- constructor body: can accepts parameters from users and initialize instance variables

eg
public class T {
    static int i = 0; //static field initializer - executed once when class is loaded (1)
    static{i=10;}//static initializer - executed once when class is loaded (2) [this is executed after static field initializer and static fields can be accessed to access instance fields, object should be created]

    int j = 0; //instance field initializer - executed once per object creation
    {j=20;} // instance initializer

    public T{ // constructor - last step of object creation
        j = 50;
        i = 50;
    }
}


# Inheritance and overriding
- i don't use inheritance to reuse code
- whatever the parent can do the child can do
- overridden methods can widen access specifier (not reduce access specifier)
  - private
  - package
  - protected
  - public
- only visible methods are inherited
  - hance no question of overriding private methods but new method with same signature can be created
- Covariance: return datatype can be changed to subtype while overriding 
- any modifier can be used
- overridden method can not throw more checked exceptions than the parent method




- composition is a feature where i have an object obj2 reference inside my class. Now i can call obj2's methods inside my class and reuse code


- run time poliformism 
    - to write poliformic code, we must code to parent interface and let jvm pick implementation in run time
    - enhanced implementation is picked in runtime

    - when you write the code if you dont know where the object referance is pointing to, then the code is polymorphic
    - where and all can you use polymorphic code
        - to accept a parameter
            - test(Animal A)
            - test(Vehicle V)
            - where Animal and Vehicle are interfaces
        - accept a generic parameter from a function
            - Animal shop("animal name")
        - Instance variable
            - instance variable can be coded to parent object and can be initialized to any child object
            - clearly we don't want to initialize it and user of the class should be allowed to initialize the variable (through setter or parameterized constructor)
        - local reference variable
    - polymorphic code is generic and flexible as its implementation is not fixed and changes during runtime
    - IMPORTANT: only public or accessible members of a class are inherited
    - constructor is not inherited


- when ever a child object is created the parent initializers and constructors must be fired
    - class loader is called and loads all classes starting from parent class
    - 
    - Object class static field initializer
    - Object class static initializer
    - Hippo class static field initializer
    - Hippo class static  initializer
    - MarriHippo class static field initializer
    - MarriHippo class static instance initializer
    - MarriMarriHippo class static field initializer (called if this is the first object)
    - MarriMarriHippo class static instance initializer(if this is the first call to marri hippo then this is definitely the first call to this class)


    - JVM calculates memory needed and allocates it

    - Object class instance field initializer
    - Object class instance initializer
    - Object class constructor

    - Hippo class instance field initializer
    - Hippo class instance initializer
    - Hippo class constructor

    - MarriHippo class instance field initializer
    - MarriHippo class instance initializer
    - MarriHippo class constructor

    - MarriMarriHippo class instance field initializer
    - MarriMarriHippo class instance initializer
    - MarriMarriHippo class constructor

- constructor execution == state creation
- compilation fails if parent obj has only parameterized constructor and child class does not explicitly call super(parameter);
 

- private methods can not be overridden as it can not be inherited

- compiler adds (if not there) "super();" as the first statement of every constructor
    - super(); calls the parents constructor
    - constructor chaining: process by which jvm executes multiple constructors of the object and its parents to create one object
    - since super(); is the first statement of every constructor, the Object class state is created first
- Object creation happens from inside to outside



## super
- access direct parent class AND CAN NOT ACCESS PRIVATE MEMBERS OF THE CLASS
- Uses: 
    - constructor chaining- child constructor to call the parent constructor, super(); must be the first statement of every constructor (only one call per constructor)
    - Access pre-overridden Methods: enables subclass to call parent's original method implementation rather than the overridden version
    - removes ambiguity when a subclass variable shadows a parent variable
        - note that a child class can have the same public instance variable name as the parent with different datatype.



## Down casting referance variable
- done to access subtype specific functionality
    - implicit up casting
        - Animal a = new Dog();
    - explicit down casting
        - Animal a = new Dog();
        - if( a instanceof Dog){
        - Dog d = (Dog) a; }// explicit down casting





## Interfaces
- implement multiple Is-A
- to hold constants
- to represent markers
- to create most generic flexible polymorphic code
    - objects from different class highricies can be passed
- interface members are by default marked public unlike classes where the default is packaged


# Access Specifiers and packages
- Class and Interface and be marked only public and package
- package is default inside classes
- package in java is used to create a grouping of related classes
- javac -d ../classes A.java
- RULE: Whenever a java program is complied, the class file must be stored under a folder named with the package name
- BestPractice: all classes must belong to a package


- package names resolves naming conflict across all classes
    - for unique package name, we follow reverse domain name naming convention
    - class users to use classes from different packages will need to use fully qualified class name or import it
        - packageNmae.className
        - import com.uttara.ashwin.zoo.Animal;
        - if I am using 2 classes with same name from different packages, then no choice but using fully qualified class name
        - use of import is soo that I do not need to use fully qualified class name and there is no other use (no linking is happening and size is not increasing)
        - package zoo is different from package zoo.test and package scoped members of zoo can not be accessed by classes in zoo.test

- protected is packaged access + access to all subclasses



# exceptions
- have a polymorphic catch for user facing method like main.
- do not have polymorphic catch for non user facing methods.
    - the catch block is to handle error. Only have catch errors that you can handle.
    - code failing is bad
    - code failing is better than code not failing and not performing its job
    - You should not have empty catch
- In catch block
    - log stack trace to file
    - print generic message to customer
- In finally code block
    - release 3rd party resources and cleanup
    - finally is executed no matter what
    - returning in finally causes silent failure NEVER RETURN IN FINALLY
        - finally return is supersedes the return in normal code block
        - finally return is supersedes the return in catch block
        - finally return is supersedes the thrown exception
    - FINALLY IS USED ONLY 3RD PARTY RESOURCES IS USED IN TRY AND IT NEEDS TO BE RELEASED
    - If there is exception in finally the finally exception is the only exception that is thrown
    - There can only be one reason for returning
    - only if System.exit(0); is executed then finally is not executed
- throw <Throwable reference>
- Forcing caller to catch exception
    - adding the throws clause in the method header
    - this is done for checked exceptions where we dont want to blame the invoker and the user has given bad inputs
    - compiler compels the invoker of the method hast to 
     - HANDLE: catch this exception 
     - DUCK: throws the same exception
- checked Exceptions
  - non runtime exceptions (all Throwables happens at runtime)
  - runtime exceptions happen due to bad code
  - checked exceptions are checked by the compiler
    - if throws has been marked for the used checked exception
    - if invoker handles it
  - no checking by compiler for unchecked exceptions and developer is expected to change code
  - checked exceptions represent business or environment failure that the user should correct
  - 



# ecplise
- ctrl + " " => code complete
- ctrl + 1 
- source










# java 8
- Default: Keyword modifier added in java 8
  - until java 8 any method added to an interfaces must be marked abstract
  - Definition: A non-abstract method within an interface that includes a body, marked with the default keyword.
  - Purpose: Enables interface evolution by providing a standard implementation that classes can inherit or override.
    - If i have a interface and I want to enhance it and add a new abstract method, then all the 1000 classes that extends it will break
    - this allows us to provide a dummy implementation in the interface that the class designer are expected to override
    - static methods can also be added to interfaces now using default (static methods can not be marked abstract)
  - Conflict Resolution: If a class implements multiple interfaces with the same default method signature, it must override the method to resolve the ambiguity.
  - default methods cant be marked final in interfaces as it is expected that the class user overrides the method
- Covariance: return datatype can be changed to subtype while overriding //introduced in jdk 5
  - 





# Maven
- It is a build automation tool
- while sharing b to b is recommended to share only .class file
- zip of all .class files into one folder is called build 
  - .jar => java archive => .class files
  - .war => web archive =. .class files + Web Resources
  - .ear => Enterprise Archive
- library: collection of .jar files
- GAV
  - group id: organization that has built the framework
  - artifact id: project or jar name
  - version: 5.3.12 => MajorVersion.MinorVersion.BugFixes
- Maven server or central repository or .m1 contains all the libraries and dependencies in one place
- .m2 or local repository or our system takes the jar files from .m1
- pom.xml captures all dependencies
  - this is requested to the m1 repo
  - the m1 repo responds with the relevant .jar files that is stored in the .m2 or local repo
- dependency hierarchy is a tree of dependencies
  - for every dependency we add, its dependencies are added creating a tree of dependencies
  - exclusions allows us to exclude the dependency that we do not need
- <scope> tag => when the dependency will be available
  - compile (default): From compile time till execution
  - runtime: only at runtime
  - provided: Given by server/container - coming from docker container
  - test: only for unit testing
  - system: from local system (our .jar file that is not in m1 repo) (<systemPath> tag should be provided)
- <properties> tag is used for version only
  - define: <key>value</key> inside the property tag
  - use: <version>${key}</version>
- BOM stands for Bill Of Materials. A BOM is a special kind of POM that is used to control the versions of a project’s dependencies and provide a central place to define and update those versions. BOM provides the flexibility to add a dependency to our module without worrying about the version that we should depend on.
- Transitive Dependencies
  - Maven can discover the libraries that are needed by our own dependencies in our pom.xml and includes them automatically. There’s no limit to the number of dependency levels that the libraries are gathered from.
  - The conflict here comes when 2 dependencies refer to different versions of a specific artifact. Which one will be included by Maven?
  - The answer here is the “nearest definition”. This means that the version used will be the closest one to our project in the tree of dependencies. This is called dependency mediation.
- parent pom
  - contains all necessary dependencies
  - child poms can inherit from the parent pom
- mvn clean package
- java -jar target/test-1.0.0.jar



# junit
- Used by developer to write test cases
- maven-surefire-plugin is used to test only a subset of test cases per environment
  - <plugin>
    - <artifactId>maven-surefire-plugin</artifactId>
      - <configuration>
        - <groups>dev</groups> // the test cases that need to be executed
        - <excludedGroups>QA</excludedGroups> // the test cases that should be excluded
      - </configuration>
    - </plugin>
- Bae64.getDecoder().decode("encrypted pwd")
  - when saving passwords, to ensure that anyone does not just see it and understand the password, it is encoded in base 64
- if multiple reference variables are pointing to the same object then => assertSame
- if multiple reference variables are pointing to multiple objects with the same content then => assertEquals
- 


# collections
- 









# Important Notes
- AS FAR AS POSSIBLE CODE TO PARENT REFERENCE
- what you inherit from the parent is what you can see
- Interface called Constants is usually created to hold all constants used in the application
- TO WRITE GENERIC FLEXIBLE POLYMORPHIC ALWAYS CODE TO INTERFACE REFERENCE
- classes with only private constructors can not be inherited
- 






11




class T
{
    int x = 10;
    int b = 20;
    sop(x);
}
p. 



# we
150c


# h


# TODO:
- retrive the questions list and solve it
- 