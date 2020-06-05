import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class Start extends StatefulWidget {
  @override
  _StartState createState() => _StartState();
}

class _StartState extends State<Start> {
  final gettingtext=TextEditingController();

   String _name='',_pass='';
   GlobalKey<FormState> _formkey=GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Container(
          margin:EdgeInsets.all(0),
          child: Scaffold(
            backgroundColor: Colors.white,
              body:Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Container(
//                    decoration: BoxDecoration(
//                      image: DecorationImage(
//                        image: AssetImage("assets/images/blackportion.png"),
//                        fit: BoxFit.fill,
//                      ),
//                    ),
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: <Widget>[
                           Text('Welcome',style: TextStyle(
                               fontFamily:('Open Sans'),
                                fontSize: 30,
                             fontWeight: FontWeight.bold
                           ),),
                          Text("""Lorem ipsum dolor sit amet, consetetur""",style: TextStyle(
                              fontFamily:('Open Sans'),
                              fontSize: 17,
                          ),),
                           Form(
                             key: _formkey,
                             child: Padding(
                               padding: const EdgeInsets.all(48.0),
                               child: Column(
                                 mainAxisAlignment: MainAxisAlignment.center,
                                 children: <Widget>[
                                   _getname(),
                                   _getpass(),
                                   SizedBox(height: 20,),
                                   RaisedButton(
                                     onPressed: (){
                                       setState(() {
                                         if(!_formkey.currentState.validate())
                                         {
                                            return;
                                         }
                                         _formkey.currentState.save();
                                          print(_name);
                                          print(_pass);

                                       });
                                     },
                                     child: Text('LogIn',style:TextStyle(fontSize: 20,fontWeight: FontWeight.bold),),
                                   )
                                 ],
                               ),
                             ),
                           )
                        ],
                      ),
                    )
                  ],
                ),
              ),
              )
          ),
      );
  }

  Widget _getname()
  {
    return TextFormField(
        decoration: InputDecoration(labelText: 'Enter your name',icon: Icon(Icons.person),border: InputBorder.none,
            enabledBorder: OutlineInputBorder(  borderSide: BorderSide(color: Colors.red),borderRadius: BorderRadius.all(10)

        ) ),
        validator: (String name)
        {
         if(name.isEmpty)
           {
              return 'Name is required';
           }
       },
       onSaved: (String name)
      {
         _name=name;
      },

    );
  }

  Widget _getpass()
  {
    return TextFormField(
      decoration: InputDecoration(labelText: 'Enter your Password',icon:Icon(Icons.security),border: InputBorder.none),
      validator: (String pass)
      {
        if(pass.isEmpty)
        {
          return 'Password is required';
        }
      },
      onSaved: (String pass)
      {
        _pass=pass;
      },

    );
  }
}


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         