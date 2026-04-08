import pandas as pd
from sklearn import datasets
from sklearn.ensemble import RandomForestClassifier
import pickle

# Load iris dataset
iris = datasets.load_iris()
X = iris.data
Y = iris.target

# Fit the model
clf = RandomForestClassifier()
clf.fit(X, Y)

# Save the trained model as a pickle string
saved_model = pickle.dumps(clf)

# Save the pickled model to a file
with open('rf_iris.pkl', 'wb') as file:
    file.write(saved_model)
