import streamlit as st

st.header("""Streamlit demo""")

st.sidebar.header("This is a **web** app")

X_test = st.sidebar.slider("Select X to get yhat",0,10,5)

st.write("X test is :", X_test)


from sklearn.linear_model import LinearRegression
lm = LinearRegression()
X = [[1],[2],[3],[4],[5]]
y = [2,3,5,8,11]
model = lm.fit(X,y)

yhat_test = model.predict([[X_test]])

st.write("b0 is ", round(model.intercept_,3))
st.write("b1 is ", round(model.coef_[0],3))
st.write("yhat test is ", yhat_test)