import streamlit as st

st.write("""# This is a simple Streamlit app""")

st.write("This is a simple **Streamlit** app")

st.write("You can enter your name below")

name = st.text_input("What is your name?")

st.write("Hello, " + name + "!")
