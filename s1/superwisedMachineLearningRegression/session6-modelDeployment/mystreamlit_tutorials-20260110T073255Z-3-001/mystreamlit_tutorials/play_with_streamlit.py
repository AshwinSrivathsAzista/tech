import streamlit as st


st.title("My First Streamlit App")


name = st.text_input("Enter your name")

if name:
    st.write(f"Hello {name}")


age = st.slider(
    "Select a number",
    min_value=0,
    max_value=100,
    value=25,
)

st.write("Slider value:", age)


course = st.selectbox(
    "Select a course",
    ["Python", "Statistics", "Machine Learning", "AI"],
)

st.write("Selected course:", course)


st.markdown(
    """
### What you just built

- A web app using only Python
- No HTML CSS or JavaScript
- Inputs and outputs update instantly
- This can be your web app framework for ML demos dashboards and tools
"""
)
