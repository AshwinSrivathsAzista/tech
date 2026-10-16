
# init

cargo new hello_world
cargo add ferris-says
cargo build
./target/debug/hello_world 

cargo run  // the above two steps in one cmd

cargo build --release  //compile it with optimizations



# java compare

- let mut guess = String::new();  =>  StringBuilder guess = new StringBuilder();
- let guess = String::new();  =>  final StringBuilder guess = new StringBuilder();



- To define a parent-child relationship in Rust, you must separate the Behavior (Traits) from the Data (Structs).
- 

- module hierarchy controls visibility and privacy
  - by default everything is private
  - pub keyword => The item is completely public and accessible from any module and external crates.

- behavior sharing between types is achieved using traits and composition.







# package
  - package has a cargo.toml and a source folder with multiple crates
  - crate consists of multiple modules (accessed through the :: syntax)
  - module is a file (or a part of the file defined my mod mod_name {normal code})
- parent module can see child module only if it is marked public
- child module can see all parent modules content


# module inheritance
// PARENT MODULE
mod plant {
    // Private by default. Only visible within `plant` and its children.
    fn photosynthesize() {
        println!("Converting light to energy...");
    }

    // CHILD MODULE
    pub mod leaf {
        // Must be marked `pub` for the parent to see it.
        pub fn grow() {
            println!("Leaf is growing.");
            
            // Child can access the parent's private items using `super::`
            super::photosynthesize(); 
        }

        // Private to `leaf`. The parent `plant` cannot call this.
        fn drop_in_autumn() {
            println!("Leaf is falling.");
        }
    }

    pub fn spring_season() {
        // Parent calling child's public function
        leaf::grow(); 
        
        // ERROR: parent cannot access child's private function
        // leaf::drop_in_autumn(); 
    }
}

fn main() {
    plant::spring_season();
}



# composition
// here a child struct has a parent struct (one of its variables)

// Define a shared behavior
trait Employee {
    fn get_id(&self) -> u32;
    fn work(&self) {
        println!("Working hard!"); // Default implementation
    }
}

// "Parent-like" base struct
struct Person {
    name: String,
    age: u8,
}

// "Child-like" struct using Composition
struct Manager {
    person: Person, // Manager "has a" Person, rather than "is a" Person
    employee_id: u32,
    team_size: u8,
}

// Implement the behavior for the struct
impl Employee for Manager {
    fn get_id(&self) -> u32 {
        self.employee_id
    }
    
    // We can override default trait methods
    fn work(&self) {
        println!("{} is managing {} people.", self.person.name, self.team_size);
    }
}

fn main() {
    let alice = Manager {
        person: Person {
            name: String::from("Alice"),
            age: 35,
        },
        employee_id: 101,
        team_size: 5,
    };

    alice.work(); // Output: Alice is managing 5 people.
    println!("ID: {}", alice.get_id());
}





# basic syntax
1. Inclusive Range (..=)
    Syntax: start..=end
    Behavior: Includes both the start and the end values.
    Example: 90..=100 matches any integer from 90 straight through to 100.
2. Exclusive Range (..)
    Syntax: start..end
    Behavior: Includes the start value, but excludes the end value.
    Example: 90..100 would match 90 through 99, but it would not match 100.

3. // The entire match block evaluates to a string slice (&str)
    let grade = match score {
        90..=100 => "A",
        80..=89  => "B",
        70..=79  => "C",
        _        => "F", // The default/catch-all case
    };

4. everything is an expression => You can actually break out of a loop and return a value from it.
    let result = loop {
        counter += 1;

        if counter == 10 {
            // You pass the value you want to return right after the 'break' keyword!
            break counter * 2; 
        }
    };

5. Vec<i32> (vector of i32) is a 
   1. dynamically resizable
   2. growable array 
   3. that holds 32-bit signed integers
   4. Contiguous Memory: Elements are stored continuously in memory making index access incredibly fast.
   5. Homogeneous: Every single item inside the vector must be an i32 (same data type)
   6. Ownership: A vector owns its elements. When the vector goes out of scope and is destroyed, all the integers inside it are cleaned up from memory too.
   7. equivalent to an ArrayList<Integer> in Java
   8. let mut numbers: Vec<i32> = Vec::new(); 
   9. numbers.push(5); // Adding elements (requires 'mut')
   10. let mut shortcut = vec![10, 20, 30]; // Infers type as Vec<i32>
   11. let first = numbers[0]; 
   12. let last = numbers.pop(); // Removing the last element
   13. let my_vec = my_vec; // Make the vector immutable and destroy the original (now only read is allowed)
   14. 
6. closure (also known as a lambda function or an anonymous function)
   1. // A standard named function
        fn add_one(x: i32) -> i32 {
            x + 1
        }

    2. // The exact same logic written as an anonymous closure
       1. let add_one_closure = |x: i32| x + 1;
       2. // If the closure is only one line long, you don't need curly braces {} or the return keyword. The result of that line is automatically returned.
    3. Closures can "Capture" their environment
       1. let tax_rate = 5;
       2. // This closure takes 'price', but it can also reach out and grab 'tax_rate'!
       3. let calculate_total = |price| price + tax_rate; 
       4. a normal function can not do this
7. Enums
   1. enums (enumerations) are types that can represent one of several variants. 
   2. Unlike Java, Rust enums can hold data
   3. example:
      1. enum TrafficLight {Red, Yellow, Green,}
      2. let current_light = TrafficLight::Yellow;
      3. match current_light { TrafficLight::Red => println!("Stop!"), .....}
   4. example 2:
      1. enum Message {
            Quit,                       // No data associated
            Move { x: i32, y: i32 },    // Anonymous struct data
            Write(String),              // Tuple data
            ChangeColor(i32, i32, i32), // Tuple data
        }

        fn process_message(msg: Message) {
            match msg {
                Message::Quit => println!("Quitting."),
                Message::Move { x, y } => println!("Moving to x: {x}, y: {y}"),
                Message::Write(text) => println!("Text message: {text}"),
                Message::ChangeColor(r, g, b) => println!("Change color to RGB({r}, {g}, {b})"),
            }
        }
      2. a
      3. 
8. Rust doesn't have a null value. Instead, it relies on two incredibly common built-in enums to handle missing data and errors safely:
   1. Option<T>: Used when a value could either be something or nothing.
      1. Example
      2.     let username: Option<String> = Some(String::from("Alice"));

            // 1. match (Exhaustive & Explicit)
            match &username {
                Some(name) => println!("Hello, {name}!"),
                None => println!("Hello, Guest!"),
            }

            // 2. if let (Concise if you only care about one variant)
            if let Some(name) = &username {
                println!("Found user: {name}");
            }

            // 3. unwrap_or (Fallback defaults)
            let final_name = username.unwrap_or(String::from("Guest"));
      3. 
   2. Result<T, E>: Used for operations that can succeed or fail.
      1. Example
      2. fn divide(numerator: f64, denominator: f64) -> Result<f64, String> {
                if denominator == 0.0 {
                    Err(String::from("Cannot divide by zero"))
                } else {
                    Ok(numerator / denominator)
                }
            }

            fn main() {
                // 1. Handling success vs failure
                match divide(10.0, 2.0) {
                    Ok(result) => println!("Result: {result}"),
                    Err(err_msg) => println!("Error: {err_msg}"),
                }

                // 2. Quick extraction with defaults
                let safe_value = divide(10.0, 0.0).unwrap_or(0.0);
            }
      3. 

9.  .and_then
   1. The purpose of .and_then() is to chain multiple steps together where any single step might fail. 
   2. If a step fails, the whole pipeline safely stops immediately.
   3. It is used for a pipeline in which data that might not exist. 
   4. It allows you to run multiple steps in a row where any step could fail or return None. 
   5. If any step fails, the whole pipeline safely stops and outputs None at the very end, preventing the program from crashing.

10. let mut vector = vec!["A".to_string(), "B".to_string(), "C".to_string()];

    for string in &vector {
        let mut new_string = string.clone();
        new_string.push_str(&string);
        vector.push(new_string) // immutable borrow occurs here // cant do a mutable borrow when there exists either a immutable borrow or immutable borrow
        print!("\ntest = {}", new_string);
    }

11. Rc<> and Arc<> => This pattern mimics garbage-collected behavior seen in Java
    1.  Arc or Atomic Reference Counted is a thread save version of Rc or Reference Counted
    2.  Rc is single-threaded. If you ever need to share a pool across multiple OS threads—such as in an asynchronous web server—you would swap Rc out for Arc (Atomic Reference Counted)
    3.  struct Pool;: A unit struct representing a resource that is expensive to create or needs to be shared (like a database connection pool).
    4.  struct RequestContext {connection_pool: Rc<Pool>}: Instead of holding a raw Pool or a standard reference (&Pool), the RequestContext holds an Rc pointer. Rc stands for Reference Counted. It wraps the heap-allocated Pool object and keeps track of how many pointers point to it
    5.  let pool = Rc::new(Pool); => a single instance of Pool is placed on the heap inside an Rc wrapper.
    6.  pool.clone(): In Rust, cloning an Rc does not copy the underlying data (the Pool). Instead, it merely clones the pointer and increments the internal reference counter by 1.
    7.  This operation is incredibly cheap (O(1) complexity).
    8.  As the application runs and individual RequestContext instances go out of scope (get dropped), Rust automatically decrements the reference count.
    9.  When the reference count finally drops to 0 (meaning the vector and all contexts are deleted), Rust safely deallocates the Pool from the heap automatically.
    10. Rc acts as a wrapper around your data. Every time you .clone() an Rc, Rust doesn't copy the actual data; it just increments a counter. When an owner goes out of scope, the counter goes down. When the counter hits zero, the data is deleted.
    11. 



# learn later pls
- what is a crate
- enums in detail
- multithreading
- obj.clone







fn endless_loop() {
print!("\n\n\n\n\n\n");
    let mut vector = vec!["A".to_string(), "B".to_string(), "C".to_string()];

    for string in &vector {
        let mut new_string = string.clone();
        new_string.push_str(&string);
        vector.push(new_string);
        print!("\ntest = {}", new_string);
    }
}
