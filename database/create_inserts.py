import sys

def create_inserts():
    file_name_to_read = sys.argv[1]
    file_name_to_write = sys.argv[2]

    table_name = raw_input('Enter table name to insert into ')
    number_columns = int(raw_input('Enter the number of columns '))

    column_names = []
    for i in range(number_columns):
        col_name = raw_input('Enter a column name ')
        column_names.append(col_name)



    columns = [[] for i in range(number_columns)]

    line_counter = 0
    with open(file_name_to_read) as f:
        for line in f:
            line_counter += 1
            i = 0
            for word in line.split(':'):
                columns[i].append(word)
                i += 1

    col_name_string = column_names[0]
    for i in range(1, number_columns):
        col_name_string = col_name_string + " , " + column_names[i]

    output_file = open(file_name_to_write, 'w')
    insert_statements = []
    for i in range(line_counter):
        is_st = "insert into " + table_name + " (" + col_name_string + ") values (\"" + columns[0][i] + "\", \"" + columns[1][i] + "\");\n"
        output_file.write(is_st)

create_inserts()
