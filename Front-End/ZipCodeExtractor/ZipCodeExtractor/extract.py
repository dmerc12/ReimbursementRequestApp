with open('file.txt', 'r') as file:
    original_data = file.readlines()

def extract_zip_codes():
    zip_codes = []

    for line in original_data:
        values = line.split('\t')
        zip_code = values[0].strip()

        if zip_code.isdigit():
            zip_codes.append(zip_code)

    return "', '".join(zip_codes)

extracted_zip_codes = extract_zip_codes()

with open('file.txt', 'w') as file:
    file.write("'" + extracted_zip_codes + "'")
