# PAIP - Clojure Edition 🧠✨

> "Paradigms of Artificial Intelligence Programming" - *Remastered*

![License](https://img.shields.io/github/license/norvig/paip-lisp)
![Clojure](https://img.shields.io/badge/language-Clojure-blue)
![Status](https://img.shields.io/badge/status-Work_in_Progress-orange)

Welcome to **PAIP - Clojure Edition**! This is a passionate hobby project dedicated to translating Peter Norvig's legendary book, *Paradigms of Artificial Intelligence Programming: Case Studies in Common Lisp*, into modern, idiomatic **Clojure**.

## 📖 About The Project

Peter Norvig's [PAIP](https://github.com/norvig/paip-lisp) is widely considered one of the best programming books ever written. It explores the depths of Lisp and AI programming. This project aims to bring those lessons into the Clojure world, adapting the Common Lisp examples to idiomatic Clojure while preserving the spirit of the original text.

Whether you're revisiting the classics or learning AI programming for the first time, this repo serves as a bridge between the elegance of Common Lisp and the practicality of Clojure.

## 🚀 Features & Implemented Modules

This project is a work in progress. Here's what has been ported so far:

### ✅ Core Utilities & Pattern Matching (`paip.auxfns`)
The backbone of many AI programs. We've implemented a robust library of auxiliary functions and a **Pattern Matching** facility that mirrors the one used in the book (and later in ELIZA).
- **`pat-match`**: Matches patterns with variables (e.g., `(?x is a ?y)`).
- **Tree Utilities**: `find-anywhere`, `flatten`, `count-anywhere`.
- **Debugging**: `dbg`, `debug`, `undebug` facilities.
- **Queues**: Persistent queue implementations.

### ✅ Introduction to Lisp (`paip.intro`)
Classic examples from the introductory chapters:
- **`first-name` / `last-name`**: Handling symbolic names and titles.
- **`power`**: Efficient exponentiation.
- **`count-atoms`**: Recursively counting atoms in nested lists.
- **`dot-product`**: Vector math operations.

## 🛠 Getting Started

### Prerequisites
- [Java Development Kit (JDK)](https://adoptopenjdk.net/) (8 or later)
- [Leiningen](https://leiningen.org/) (Build tool)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/paip-clojure.git
   cd paip-clojure
   ```

### Running the Code
Start a REPL to interact with the code:
```bash
lein repl
```

Inside the REPL, you can try out the pattern matcher:
```clojure
(require '[paip.auxfns :refer [pat-match]])

(pat-match '(?x is a ?y) '(This is a test))
;; => {?x This, ?y test}
```

### Running Tests
We have a suite of tests to ensure the translation is accurate.
```bash
lein test
```

## 🗺 Roadmap & Status

- [x] **Chapter 1**: Introduction to Lisp
- [x] **Chapter 2**: A Simple Lisp Program (Pattern Matching core)
- [ ] **Chapter 3**: Overview of Lisp
- [ ] **Chapter 4**: GPS: The General Problem Solver
- [ ] **Chapter 5**: ELIZA: Dialog with a Machine
- [ ] **Chapter 6**: Building Software Tools
- [ ] ... and beyond!

## 🤝 Contributing

Contributions are welcome! If you're working through PAIP and want to submit a translation for a chapter, feel free to open a Pull Request.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/Chapter4-GPS`)
3. Commit your Changes (`git commit -m 'Add GPS implementation'`)
4. Push to the Branch (`git push origin feature/Chapter4-GPS`)
5. Open a Pull Request

## 📜 License

Distributed under the MIT License. See `LICENSE` for more information.

## 🙏 Acknowledgements

* **Peter Norvig** for the original [PAIP](https://github.com/norvig/paip-lisp) masterpiece.
* The Clojure community for being awesome.
