# CPS Monitoring using MDD
This folder contains all projects of the MDD pipeline, as well as utility projects.

## cpsaml
The [cpsaml](/cpsaml) project contains the CPS Architecture Modeling Language (CPSAML). It is based on the Eclipse Modeling Framework (EMF).

## cpsaml.xtext
The XText projects contain a textual concrete syntax for CPSAML.

## cpsaml.transform
This project contains a transformation that takes a CPSAML model as input and generates a SysML 2 Project with multiple files.

## sysml2.codegen
This is the code generator and last piece of the pipeline. It takes a SysML 2 project as an input and generates multiple projects including source code in different languages, like Python, Java and Typescript.